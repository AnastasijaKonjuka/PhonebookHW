public class ContactNotFoundException extends Exception {
    public ContactNotFoundException() {
        super("Contact not found!");
    }

    public ContactNotFoundException(String message) {super(message);}
}
