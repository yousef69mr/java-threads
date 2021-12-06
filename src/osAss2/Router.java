package osAss2;



public class Router{


    private static int connections;
    private int size ; // the buffer bound

    private Semaphore spaces;
  //  Semaphore devices = new Semaphore(0);

    Router(int length){
        setSize(length);
        spaces = new Semaphore(size,this);
    }

    public void process(Device device){

        if(spaces.getValue()-1<0) {
            System.out.println("(" + device.getDeviceName() + ")" + "(" + device.getDeviceType() + ")" + " arrived and waiting");
        }
        spaces.P();

        if(spaces.getValue()<=size&&spaces.getValue()>=0) {
            System.out.println("(" + device.getDeviceName() + ")" + "(" + device.getDeviceType() + ")" + " arrived");
        }

        connect(device);
        doingActivity(device);
        disconnect(device);
        spaces.V();

    }
    /*
    public void process(Device device){

        spaces.P();

        synchronized (this){
            connections++;
            System.out.println("Connection " +connections+" : "+ device.getDeviceName()+" Occupied");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Connection " +connections+" : "+ device.getDeviceName()+" Login");

        }

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Connection " +connections+" : "+ device.getDeviceName() +" performs online activity");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this){
            connections--;

            if(connections==0){
                int c =connections+1;
                System.out.println("Connection " +c+" : "+ device.getDeviceName() +" Logged out");
            }else{
                System.out.println("Connection " +connections+" : "+ device.getDeviceName() +" Logged out");
            }

        }

        spaces.V();

    }
*/

    public void connect(Device device){

        synchronized (this){
            connections++;
            System.out.println("Connection " +connections+" : "+ device.getDeviceName()+" Occupied");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Connection " +connections+" : "+ device.getDeviceName()+" Login");

        }

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void doingActivity(Device device){

        System.out.println("Connection " +connections+" : "+ device.getDeviceName() +" performs online activity");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void disconnect(Device device){

        synchronized (this){

            if(connections==0){
                int c =connections+1;
                System.out.println("Connection " +c+" : "+ device.getDeviceName() +" Logged out");
            }else{
                System.out.println("Connection " +connections+" : "+ device.getDeviceName() +" Logged out");
            }
            connections--;
        }

    }

    void setSize(int size){
        this.size=size;
    }
/*
   int getNumberOfConnectedDevices(){
        return devices.getValue();
   }
*/
    /*
   int getConnections(){
        return this.connections;
   }

    public Semaphore getSpaces() {
        return this.spaces;
    }

     */
}
