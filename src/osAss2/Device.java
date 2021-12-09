package osAss2;

public class Device extends Thread{
    private String name;
    private String type;
    private Router router;
    private boolean runBefore;

    Device(String name,String type,Router router){
        setDeviceName(name);
        setTypeName(type);
        setRouter(router);
        this.runBefore=false;
    }

    void setRouter(Router r){
        this.router=r;
    }

    boolean getRunBefore(){
        return this.runBefore;
    }

    void setRunBefore(){
        this.runBefore=true;
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
