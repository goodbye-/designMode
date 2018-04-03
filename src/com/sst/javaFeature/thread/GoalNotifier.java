package com.sst.javaFeature.thread;

public class GoalNotifier implements Runnable {
    public volatile boolean goal = false; //jmm
    public boolean isGoal() {
        return goal;
    }

    public void setGoal(boolean goal) {
        this.goal = goal;
    }

    @Override
    public void run() {
        while (true) {
            if (isGoal()) {
                System.out.println("Goal !!!!!!");

                // Tell the referee the ball is in.
                // ...

                // reset goal flag
                setGoal(false);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Game begun! Init goalNotifier thread
        GoalNotifier goalNotifier = new GoalNotifier();
        Thread goalNotifierThread = new Thread(goalNotifier);
        goalNotifierThread.start();

        // After 3s
        Thread.sleep(3000);
        // Goal !!!
        goalNotifier.setGoal(true);
    }
}
