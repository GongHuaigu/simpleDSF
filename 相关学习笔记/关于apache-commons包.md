### commons-pool2 对象池
作用：对象池化技术，缓存一些初始化很耗时的对象，避免重复创建。  
#### 核心类
- GenericObjectPool  
对象池实现，使用时传入对象池的设置GenericObjectPoolConfig和池化对象的工厂类PooledObjectFactory。
 两个比较重要的方法：  
   - 从对象池获取一个对象：borrowObject
   - 归还到对象池 returnObject(T obj)
- PooledObjectFactory  
对对象池中的对象创建和管理