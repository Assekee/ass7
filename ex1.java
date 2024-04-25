import java.util.*;

class SupportRequest {
    private int id;
    private String description;
    private int priority;

    public SupportRequest(int id, String description, int priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
    }

    
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}

interface SupportHandler {
    void handleRequest(SupportRequest request);
}

class HardwareSupportHandler implements SupportHandler {
    private SupportHandler nextHandler;

    @Override
    public void handleRequest(SupportRequest request) {
        if (request.getPriority() <= 3) {
            System.out.println("Hardware support team is handling request: " + request.getDescription());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("No suitable handler found for request: " + request.getDescription());
        }
    }

    public void setNextHandler(SupportHandler handler) {
        nextHandler = handler;
    }
}

class SoftwareSupportHandler implements SupportHandler {
    private SupportHandler nextHandler;

    @Override
    public void handleRequest(SupportRequest request) {
        if (request.getPriority() <= 5) {
            System.out.println("Software support team is handling request: " + request.getDescription());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("No suitable handler found for request: " + request.getDescription());
        }
    }

    public void setNextHandler(SupportHandler handler) {
        nextHandler = handler;
    }
}


class NetworkSupportHandler implements SupportHandler {
    @Override
    public void handleRequest(SupportRequest request) {
        System.out.println("Network support team is handling request: " + request.getDescription());
    }
}

public class ex1 {
    public static void main(String[] args) {

        HardwareSupportHandler hardwareHandler = new HardwareSupportHandler();
        SoftwareSupportHandler softwareHandler = new SoftwareSupportHandler();
        NetworkSupportHandler networkHandler = new NetworkSupportHandler();

        
        hardwareHandler.setNextHandler(softwareHandler);
        softwareHandler.setNextHandler(networkHandler);

        
        SupportRequest request1 = new SupportRequest(1, "Hardware issue", 2);
        SupportRequest request2 = new SupportRequest(2, "Software issue", 4);
        SupportRequest request3 = new SupportRequest(3, "Network issue", 6);

        
        hardwareHandler.handleRequest(request1);
        hardwareHandler.handleRequest(request2);
        hardwareHandler.handleRequest(request3);
    }
}
