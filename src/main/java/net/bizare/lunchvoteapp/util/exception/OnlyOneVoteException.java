package net.bizare.lunchvoteapp.util.exception;

public class OnlyOneVoteException extends RuntimeException {
    public OnlyOneVoteException(String message) {
        super(message);
    }
}
