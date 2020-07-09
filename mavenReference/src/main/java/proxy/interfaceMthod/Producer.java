package proxy.interfaceMthod;

/**
 * 一个生产者
 */
public class Producer implements IProducer {
    /**
     * 销售产品
     *
     * @param money
     */
    @Override
    public void saleProducer(float money) {
        System.out.println("生产者销售产品：" + money);
    }

    /**
     * 生产厂房售后
     *
     * @param money
     */
    @Override
    public void afterService(float money) {
        System.out.println("生产者提供售后，拿到钱：" + money);
    }
}
