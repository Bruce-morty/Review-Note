```java
interface Network{
    public void browse();		//定义方法
}

class Real implements Network{
    public void browse() {
        System.out.println("surfing");
    }
}

class Proxy implements Network{
    // 创建接口对象，来接受子类
    private Network network;
    
    // 设置代理的真实操作，设置代理的子类
    public Proxy(Network network) {
        this.network = network;
    }
    
    // 定义一个新的方法，用来加强被代理对象
    public void check () {
        System.out.println("check user real");
    }
    
    public void browse() {
        this.check();				// 加强代理对象
        this.network.browse();		//调用被代理对象的方法
    }
}

public class TestDemo{
    public void static main(String[] args) {
        Network network = null;
        // 把被代理对象的实例传入Proxy中，这样首先构造函数中的network就是real的实例
        // 然后调用browse方法时，也会调用check方法，network的browse方法也就是Real中的，未增强的方法
        network = new Proxy(new Real());
        network.browse();
    }
}

/*
输出结果：
surfing
check user real
*/
```

---

----

-----

分割线---   -----

---

----



---

----

` public static void mani` this is a module about ``  中间包含一段代码。

