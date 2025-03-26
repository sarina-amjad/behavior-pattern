import java.util.ArrayList;
import java.util.List;

// Subject class with attach, detach, and notify methods
class Subject {
    private final List<Observer> observers = new ArrayList<>();
    private int state;

    // Getter for state
    public int getState() {
        return state;
    }

    // Setter for state that notifies active observers
    public void setState(int state) {
        this.state = state;
        notifyActiveObservers();  // Only notify active observers
    }

    // Method to attach an observer
    public void attach(Observer observer) {
        observers.add(observer);
    }

    // Method to detach an observer
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    // Notify only active observers
    public void notifyActiveObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

// Abstract Observer class
abstract class Observer {
    protected Subject subject;

    // Abstract update method
    public abstract void update();
}

// ConcreteObserver class
class ConcreteObserver extends Observer {

    public ConcreteObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);  // Attach observer to subject
    }

    @Override
    public void update() {
        System.out.println("State updated to: " + subject.getState());
    }
}

// Main class (Demo)
public class ObserverPatternDemo {
    public static void main(String[] args) {
        // Create a new subject
        Subject subject = new Subject();

        // Create observers and attach them to the subject
        ConcreteObserver observer1 = new ConcreteObserver(subject);
        ConcreteObserver observer2 = new ConcreteObserver(subject);

        // Change the state and notify observers
        System.out.println("First state change: 15");
        subject.setState(15);

        // Detach observer2 and notify remaining active observers
        System.out.println("Detaching observer2...");
        subject.detach(observer2);

        // Change the state again
        System.out.println("Second state change: 20");
        subject.setState(20);  // Only observer1 will be notified now
    }
}

