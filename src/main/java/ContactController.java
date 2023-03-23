import java.util.ArrayList;

public class ContactController {

    ArrayList<Contact> contacts = new ArrayList<>();

    public ContactController() {}

    public void addContact(Contact contact) {this.contacts.add(contact);}

    //to think&upgrade - finds only first contact if several contacts contain the same part of name/phoneNum
    public Contact findContactByName(String name) throws Exception{
        for (Contact contact : this.contacts) {
            if (contact.getName().contains(name)) return contact;
        }
        throw new ContactNotFoundException();
    }

    public Contact findContactByNum(String phoneNum) throws Exception {
        for (Contact contact : this.contacts) {
            if (contact.getPhoneNum().contains(phoneNum)) return contact;
        }
        throw new ContactNotFoundException();
    }

    public void removeContact(Contact contact) {this.contacts.remove(contact);}

    public void updateContact(Contact contact, String name, String phoneNum, String email) {
        contact.setName(name);
        contact.setPhoneNum(phoneNum);
        contact.setEmail(email);
    }

    public void seeAllContacts() {
        System.out.println(contacts);
    }
}
