package manager;

import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.GroupData;
import model.ContactData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.jpa.HibernatePersistenceConfiguration;

import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);

        sessionFactory = new Configuration()
                .addAnnotatedClass(GroupRecord.class)
                .addAnnotatedClass(ContactRecord.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull&useSSL=false")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    static List<GroupData> convertedList(List<GroupRecord> records) {
        List<GroupData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    public List<GroupData> getGroupList() {
        return convertedList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public void createContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertContact(contactData));
            session.getTransaction().commit();
        });
    }


    static List<ContactData> convertContactList(List<ContactRecord> records) {
        List<ContactData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convertContact(record)
            );
        }
        return result;
    }

    private static ContactData convertContact(ContactRecord record) {
        return new ContactData()
                .withIdName(
                        "" + record.id,
                        record.firstname,
                        record.lastname
                );
    }

    private static ContactRecord convertContact(ContactData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(
                Integer.parseInt(id),
                data.Firstname(),
                data.Lastname()
        );
    }

    public List<ContactData> getContactList() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }


    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }


    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.find(GroupRecord.class, group.id()).contacts);
        });
    }

    public int getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class)
                    .getSingleResult()
                    .intValue();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertGroup(groupData));
            session.getTransaction().commit();
        });
    }

    // Вспомогательный метод для конвертации модели данных в сущность Hibernate
    private static GroupRecord convertGroup(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        // Создаем Record с id, name, header, footer
        return new GroupRecord(
                Integer.parseInt(id),
                data.name(),
                data.header(),
                data.footer()
        );
    }
}

