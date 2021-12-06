package osAss2;

public class Device extends Thread{
    private String name;
    private String type;
    private Router router;

    Device(String name,String type,Router router){
        setDeviceName(name);
        setTypeName(type);
        setRouter(router);
    }

    void setRouter(Router r){
        this.router=r;
    }

    Router getRouter(){
        return this.router;
    }

    void setDeviceName(String name){
        this.name=name;
    }

    String getDeviceName(){
        return this.name;
    }

    void setTypeName(String type){
        this.type=type;
    }

    String getDeviceType(){
        return this.type;
    }


    public void run(){

        router.process(this);
        /*
        router.connect(this);
        router.doingActivity(this);
        router.disconnect(this);
*/
    }
}
