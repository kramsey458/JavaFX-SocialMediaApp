package FacebookGUI.DataModel;

public interface Observable {
    void setObserver(Observer o);
    void notifyObserver();
}
