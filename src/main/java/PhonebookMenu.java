import javax.imageio.IIOException;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;


public class PhonebookMenu {

    private final ContactController contactController = new ContactController();

    public PhonebookMenu() throws IOException {};


    public void start() {
       JOptionPane.showMessageDialog(null, "Welcome to Phonebook! Please choose an option on the next prompt");
        try {
            this.displayMainMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void displayMainMenu() throws Exception {
        String[] availableOptions = {"Add contact", "Find contact by Name", "Find contact by Number",
                "Update contact", "Remove contact", "See all contacts", "Export contacts", "Close app"};
        String option = (String) JOptionPane.showInputDialog(
                null,
                "Select option",
                "Phonebook Menu",
                JOptionPane.QUESTION_MESSAGE,
                null,
                availableOptions,
                availableOptions[0]
        );

        switch (option) {
            case "Add contact" -> this.handleAddContact();
            case "Find contact by Name" -> this.handleFindByName();
            case "Find contact by Number" -> this.handleFindByNumber();
            case "Update contact" -> this.handleUpdateContact();
            case "Remove contact" -> this.handleRemoveContact();
            case "See all contacts" -> this.contactController.seeAllContacts();
            case "Export contacts" -> this.handleContactExport();
            case "Close app" -> System.exit(0);
        }

        this.displayMainMenu();
    }




    private void handleAddContact() { //to add exception if contact already exists

        String name = this.getUserInput("Enter contact name");
        String phoneNum = this.getUserInput("Enter phone number");
        String email = this.getUserInput("Enter email");

        Contact contact = new Contact(name, phoneNum, email);

        this.contactController.addContact(contact);
        this.displayMessage("New contact created: " + contact);

    }

    private void handleRemoveContact() {
        String name = this.getUserInput("Enter contact name to remove");
        try {
            this.displayMessage(String.valueOf(this.contactController.findContactByName(name)));

            Contact contactToRemove = this.contactController.findContactByName(name);

            this.contactController.removeContact(contactToRemove);
            this.displayMessage("Contact removed: " + contactToRemove);

        } catch (Exception exception) {
            this.displayMessage("Contact not found!");
        }
    }

    private void handleFindByName() {
        String name = this.getUserInput("Enter contact name");
        try {
            this.displayMessage(String.valueOf(this.contactController.findContactByName(name)));
        } catch (Exception exception) {
            this.displayMessage("Contact not found!");
        }
    }

    private void handleFindByNumber() {
        String phoneNum = this.getUserInput("Enter Phone Number");
        try {
            this.displayMessage(String.valueOf(this.contactController.findContactByNum(phoneNum)));
        } catch (Exception exception) {
            this.displayMessage("Contact not found!");
        }
    }

    private void handleUpdateContact() { //possibilities to upgrade - ask find by name or by nr?; Does not fill the fields -> stays the same
        String name = this.getUserInput("Enter contact name");
        try {
            this.displayMessage(String.valueOf(this.contactController.findContactByName(name)));

            Contact contactToUpdate = this.contactController.findContactByName(name);
            String newName = this.getUserInput("Enter new contact name");
            String newPhoneNum = this.getUserInput("Enter new Phone number");
            String newEmail = this.getUserInput("Enter new email");

            this.contactController.updateContact(contactToUpdate, newName, newPhoneNum, newEmail);
            this.displayMessage("Contact updated: " + contactToUpdate);

        } catch (Exception exception) {
            this.displayMessage("Contact not found!");
        }

    }

    private void handleContactExport() {
        try {
            File phonebook = new File("phonebook.csv");
            if (phonebook.createNewFile()) {
                System.out.println("File created: " + phonebook.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter myWriter = new FileWriter("phonebook.csv");
            myWriter.write(contactController.contacts.toString());
            myWriter.close();

            this.displayMessage("File is ready.");

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private String getUserInput(String message) {
        return JOptionPane.showInputDialog(null, message);
    }

    private void displayMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }


}
