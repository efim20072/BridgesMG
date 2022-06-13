package me.elijahproductions.bridgesmg.infrastructure.minigame.task;

import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class CountdownTask extends BukkitRunnable {

    public interface OnEndListener {
        void onEnd();
    }

    public interface OnCancelListener {
        void OnCancel();
    }

    private final OnEndListener listener;

    private OnCancelListener onCancelListener = () -> {};

    public CountdownTask(OnEndListener listener) {
        this.listener = listener;
    }

    public CountdownTask(int timeLeft, OnEndListener listener) {
        this.timeLeft = timeLeft;
        this.listener = listener;
    }

    private int timeLeft = 20; //TODO magic number

    @Override
    public void run() {
        timeLeft--;
        if (timeLeft <= 0){
            cancel();
            listener.onEnd();
            return;
        }
        System.out.println(timeLeft);
    }

    @Override
    public synchronized void cancel() throws IllegalStateException {
        super.cancel();
        onCancelListener.OnCancel();
    }

    public void setOnCancelListener(@NotNull OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
    }
}
