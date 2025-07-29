package front_controller_pattern;

import lombok.Data;

@Data
public class FrontController {
    private final Dispatching dispatching = new Dispatching();

    public boolean isAvailable(){
        return true;
    }
    public void trackRequest(String request){
        System.out.printf("Tracking request of %s successful.\n", request);
    }
    public void dispatchRequest(String request){
        trackRequest(request);
        if(isAvailable()){
            dispatching.dispatch(request);
        }
    }
}
