package edu.yu.cs.intro.ga;
public class Event{
/**
* @throws IllegalArgumentException if path or acquisition are invalid, or if duration or
conversion are negative
* @param path
* @param duration
* @param conversion
* @param acquisition
* @see Validators#isValidPath(String)
* @see Validators#isValidAcquisition(String)
*/
private String path;
private int duration;
private double conversion;
private String acquisition;

public Event(String path, int duration, double conversion, String acquisition){
    this.path= path;
    this.duration= duration;
    this.conversion= conversion;
    this.acquisition= acquisition;
	
    if (!Validators.isValidPath(path)) {
            throw new IllegalArgumentException();
        }
	if (!Validators.isValidAcquisition(acquisition)) {
            throw new IllegalArgumentException();
        }
	if (duration < 0) {
            throw new IllegalArgumentException();
        }
	if (conversion < 0.0) {
            throw new IllegalArgumentException();
        }


}
public String getPath(){
return this.path;
}
public String getAcquisition(){
return this.acquisition;
}
public int getDuration(){
return this.duration;
}
public double getConversion(){
return this.conversion;
}

}

 //this always refers to THIS class variable (our private one).