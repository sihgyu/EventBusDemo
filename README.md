# EventBusDemo
一个EventBus结合Spring的使用demo

### 简介
Guava在guava-libraries中为我们提供了事件总线EventBus库，它是事件发布订阅模式的实现，让我们能在领域驱动设计(DDD)中以事件的弱引用本质对我们的模块和领域边界很好的解耦设计。
 首先Guava为我们提供了同步事件EventBus和异步实现AsyncEventBus两个事件总线，他们都不是单例的，官方理由是并不想我们我们的使用方式。当然如果我们想其为单例，我们可以很容易封装它，一个单例模式保证只创建一个实例就对了。
### maven依赖
```
<!-- https://mvnrepository.com/artifact/com.google.guava/guava -->

        <dependency>

            <groupId>com.google.guava</groupId>

            <artifactId>guava</artifactId>

            <version>19.0</version>

        </dependency>
```

### 内部方法
```java
	//注册
    public void register(Object object) {
        this.subscribers.register(object);
    }
	//反注册
    public void unregister(Object object) {
        this.subscribers.unregister(object);
    }
	//发布订阅
    public void post(Object event) {
        Iterator<Subscriber> eventSubscribers = this.subscribers.getSubscribers(event);
        if (eventSubscribers.hasNext()) {
            this.dispatcher.dispatch(event, eventSubscribers);
        } else if (!(event instanceof DeadEvent)) {
            this.post(new DeadEvent(this, event));
        }

    }
   
   ```
   ### 注册
   这里通过Spring的@PostConstruct注解进行注册
   调用了EventBus的方法：register
   ```java
       @PostConstruct
    private void init(){
        eventBus = new AsyncEventBus(Executors.newFixedThreadPool(4));
        eventBus.register(listerService);
    }
    ```
   ### 订阅
   EventBus的订阅很简单只需要一个注解@Subscribe+一个有一个入参的方法即可
   一个订阅者的demo：
   ```java
    @Subscribe
    public void lister(Integer integer){
        System.out.println(integer);
        log.info("lister has receive a message :{}",integer);
    }
   ```
   Guava发布的事件默认不会处理线程安全的，但我们可以标注@AllowConcurrentEvents来保证其线程安全
  
  ### 发布
  对于事件源，则可以通过post方法发布事件。 正在这里对于Guava对于事件的发布，是依据上例中订阅方法的方法参数类型决定的，换而言之就是post传入的类型和其基类类型可以收到此事件。例如下例
 ```java
  public void doEventBus(Integer integer){
      log.info("doEventBus --> {}",integer);
      eventBus.post(integer);
  }
 ```


