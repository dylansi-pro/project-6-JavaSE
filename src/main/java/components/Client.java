package components;

// 1.1.1 Creation of the client class
public class Client {
    private String name;
    private String firstName;
    private static int counter = 0;
    private int clientNumber;

    public Client(String name,String firstName ,int clientNumber) {
        this.name = name;
        this.firstName = firstName;
        this.clientNumber = counter++;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    @Override
    public String toString() {
        return "Client [" + clientNumber + "] : " + firstName + " " + name;
    }

}
