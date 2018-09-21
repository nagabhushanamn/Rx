package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class DoorEvent {
	// ...
}

interface DoorListener {
	void start(DoorEvent event);

	void stop(DoorEvent event);
}

class Door {
	private List<DoorListener> doorListeners = new ArrayList<>();

	public void addDoorListener(DoorListener doorListener) {
		doorListeners.add(doorListener);
	}

	public void removeDoorListener(DoorListener doorListener) {
		doorListeners.remove(doorListener);
	}

	public void open() {
		System.out.println("open..");
		DoorEvent doorEvent = new DoorEvent();
		doorListeners.stream().forEach((listener) -> listener.start(doorEvent));
	}

	public void close() {
		System.out.println("close..");
		DoorEvent doorEvent = new DoorEvent();
		doorListeners.stream().forEach((listener) -> listener.stop(doorEvent));
	}
}

class Light implements DoorListener {

	@Override
	public void start(DoorEvent event) {
		System.out.println("Light ON");
	}

	@Override
	public void stop(DoorEvent event) {
		System.out.println("Light OFF");
	}

}

class AC implements DoorListener {
	@Override
	public void start(DoorEvent event) {
		System.out.println("AC ON");
	}

	@Override
	public void stop(DoorEvent event) {
		System.out.println("AC OFF");
	}
}

public class Observer_Pattern {

	public static void main(String[] args) throws InterruptedException {

		Door door = new Door();

		Light light = new Light();
		AC ac = new AC();

		door.addDoorListener(light);
		door.addDoorListener(ac);

		TimeUnit.SECONDS.sleep(3);
		door.open();
		TimeUnit.SECONDS.sleep(3);
		door.close();

	}

}
