package Reverse;

/* renamed from: c */
public final class KeyboradThread extends Thread {

   
    private int keyFlag  = 0;

   
    public volatile boolean running = false;

   
    private int delay = 100;

    public KeyboradThread(int flag) {
        this.keyFlag  = flag;
        if (flag != 0) {
            this.running = true;
        }
        setPriority(Thread.NORM_PRIORITY);
        start();
        this.delay = 290;
    }

    public final void run() {
        while (this.running) {
            try {
                sleep((long) this.delay);
            } catch (InterruptedException e) {
            }
            if ((this.keyFlag  == 4) && this.running) {
                if (this.delay > 200) {
                    this.delay -= 200;
                }
                if (Main.dayOFShamsiYear < 365) {
                    Main.dayOFShamsiYear++;
                } else {
                    this.running = false;
                }
                DateManager.prepare();
                Main.mainScreen.repaint();
            }
            if ((this.keyFlag  == 3) && this.running) {
                if (this.delay > 200) {
                    this.delay -= 200;
                }
                if (Main.dayOFShamsiYear > 0) {
                    Main.dayOFShamsiYear--;
                } else {
                    this.running = false;
                }
                DateManager.prepare();
                Main.mainScreen.repaint();
            }
        }
    }
}
