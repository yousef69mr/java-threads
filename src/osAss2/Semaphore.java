package osAss2;

public class Semaphore {
    private int value;
    private Router router;
    protected Semaphore(Router r) {
        value = 0 ;
        this.router=r;
    }

    protected Semaphore(int initial,Router r) {
        this.value = initial ;
        this.router=r;
    }
    public synchronized void P() {

        value-- ;
        if (value < 0){
            try {

                wait() ;
            }
            catch( InterruptedException e ) {

                System.out.println("Error in waiting");
            }
        }
    }
    public synchronized void V() {
        value++ ;
        if (value <= 0){
            notify() ;
        }
    }

    int getValue(){
        return this.value;
    }
/*
    Router getRouter(){
        return router;
    }

 */
}
