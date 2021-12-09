package osAss2;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Network{

    private GUI gui;
    static Router router;
    static ArrayList<Device> devices =new ArrayList<Device>();

    Network(int connections,GUI gui){
        this.gui=gui;
        router =new Router(connections,this);
    }

    Network(GUI gui){
        this.gui=gui;
    }

    GUI getGUI(){
        return this.gui;
    }

    void createRouter(int connections){
        router =new Router(connections,this);
    }


    Router getRouter(){
        return this.router;
    }

    ArrayList<Device> getDevices(){
        return this.devices;
    }
    /*
	public static void main(String[] args) throws Exception {

		int i,j;
		Scanner scan=new Scanner(System.in);
		System.out.println("What is the number of WI-FI Connections?");
		int connections =scan.nextInt();
		router=new Router(connections);
		//router.setSize(connections);

		System.out.println("What is the number of devices Clients want to connect?");
		int  numOfDevices =scan.nextInt();

		for (i=0;i<numOfDevices;i++){
			System.out.println("/////////");
			String input;
			String[] deviceData =new String[2];
			for(j=0;j<2;j++){

				input =scan.next();
				deviceData[j]=input;
			}

			Device device =new Device(deviceData[0],deviceData[1],router);
			devices.add(device);

		}
*/
/*
		ExecutorService executor = Executors.newCachedThreadPool();

		for(i=0;i<numOfDevices;i++){
			executor.submit(new Runnable() {
				@Override
				public void run() {
					for(j=0;j<devices.size();j++){
						System.out.println("#######");
						router.connect(devices.get(j));
						router.doingActivity(devices.get(j));
						router.disconnect(devices.get(j));
					}
				}
			});

		}

		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);

*/

/*
		//System.out.println(devices.get(0).getDeviceName()+"\n"+devices.get(0).getRouter());
		//	devices.get(0).start();

		for (i=0;i<devices.size();i++){
			devices.get(i).start();
		}

		//Semaphore s=new Semaphore(1);

		//System.out.println(router.getNumberOfConnectedDevices());

		//System.out.println(router.getConnections());

	}
*/


    public static void main(String[] args) {
        new GUI();
    }
}
