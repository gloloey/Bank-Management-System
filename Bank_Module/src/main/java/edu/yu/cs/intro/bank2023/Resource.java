package edu.yu.cs.intro.ga;
public class Resource{
/** 
*
*  @throws IllegalArgumentException if the path is invalid
* @param path the path which this resource represents
3
* @see Validators#isValidPath(String)
*/
    

private int len = 0;
private String path;
private Event[] arrE = new Event[0];


public Resource(String path){
	if (!Validators.isValidPath(path)) {
            throw new IllegalArgumentException();
        }
    this.path = path; 
}

public String getPath(){
return this.path;
}

/**
* @throws IllegalArgumentException if the event is null or if the event's path doesn't 
match this resources path
* @param e the event to add to this resource
*/ 


public void addEvent(Event e) { //prot
    if (e == null || !e.getPath().equals(this.path)) {
        throw new IllegalArgumentException("Invalid event or path mismatch");
    }

    // Create a new array with increased size
    Event[] newArr = new Event[len + 1];

    // Copy elements from the original array to the new array
    for (int i = 0; i < len; i++) {
        newArr[i] = arrE[i];
    }

    // Add the new event to the end of the new array
    newArr[len] = e;

    // Update the array reference
    arrE = newArr;

    // Increment the length
    len++;
}


/**
* @return the events in the order in which they were added to the Resource. The array 
should not contain any extra/null entries. Must return a COPY of the event array, not the 
array which is Resource's instance variable.
*/
public Event[] getEventsInChronologicalOrder(){
	Event[] arrChrono = new Event[len];
    for(int i=0; i<len; i++){ //lo riempi
        arrChrono[i] = arrE[i];
    }
    return arrChrono;
}

/**
* @return the duration of all the events on this resource combined
*/
public int getTotalDuration(){
   int totDuration = 0;
    for (int i=0; i<len; i++){
        totDuration += arrE[i].getDuration();
    }
    return totDuration;
}

/**
* @return the conversion value of all the events on this resource combined
*/
public double getTotalConversion(){
    double totConversion = 0; //qui cera un err
    for (int i=0; i<len; i++){
        totConversion += arrE[i].getConversion();
    }
    return totConversion;
}

/**
* @return an array of length 3, in which [0] holds the count of events acquired via 
search, [1] holds the total for direct, [2] holds the total for referral
* @see Validators#A_SEARCH
* @see Validators#A_DIRECT
* @see Validators#A_REFERRAL     //!!
*/
public int[] getTotalAcquisitionCounts(){
    //search - direct - referral
int[] sdr = new int[3];
int totS = 0;
int totD = 0;
int totR = 0;
for (int i=0; i<len; i++){
    if (arrE[i].getAcquisition().equals(Validators.A_SEARCH)){
        totS++;
    }
    if (arrE[i].getAcquisition().equals(Validators.A_DIRECT)){
        totD++;
    }
    if (arrE[i].getAcquisition().equals(Validators.A_REFERRAL)){
        totR++;
    }
}
sdr[0] = totS;
sdr[1] = totD;
sdr[2] = totR;
return sdr;
}
}