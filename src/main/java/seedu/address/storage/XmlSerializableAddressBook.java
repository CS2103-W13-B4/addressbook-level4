package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.lecturer.Lecturer;
import seedu.address.model.module.ReadOnlyLesson;
import seedu.address.model.module.Remark;

//@@author angtianlannus
/**
 * An Immutable AddressBook that is serializable to XML format
 */
@XmlRootElement(name = "addressbook")
public class XmlSerializableAddressBook implements ReadOnlyAddressBook {


    @XmlElement
    private List<XmlAdaptedLesson> lessons;
    @XmlElement
    private List<XmlAdaptedLecturer> lecturers;
    @XmlElement
    private List<XmlAdaptedRemark> remarks;


    /**
     * Creates an empty XmlSerializableAddressBook.
     * This empty constructor is required for marshalling.
     */
    public XmlSerializableAddressBook() {
        lessons = new ArrayList<>();
        lecturers = new ArrayList<>();
        remarks = new ArrayList<>();
    }

    /**
     * Conversion
     */
    public XmlSerializableAddressBook(ReadOnlyAddressBook src) {
        this();

        lessons.addAll(src.getLessonList().stream().map(XmlAdaptedLesson::new).collect(Collectors.toList()));
        lecturers.addAll(src.getLecturerList().stream().map(XmlAdaptedLecturer::new).collect(Collectors.toList()));
        remarks.addAll(src.getRemarkList().stream().map(XmlAdaptedRemark::new).collect(Collectors.toList()));
    }

    @Override
    public ObservableList<ReadOnlyLesson> getLessonList() {
        final ObservableList<ReadOnlyLesson> lessons = this.lessons.stream().map(p -> {
            try {
                return p.toModelType();
            } catch (IllegalValueException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toCollection(FXCollections::observableArrayList));
        return FXCollections.unmodifiableObservableList(lessons);
    }

    @Override
    public ObservableList<Lecturer> getLecturerList() {
        final ObservableList<Lecturer> lecturers = this.lecturers.stream().map(p -> {
            try {
                return p.toModelType();
            } catch (IllegalValueException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toCollection(FXCollections::observableArrayList));
        return FXCollections.unmodifiableObservableList(lecturers);
    }

    //@@author junming403
    @Override
    public ObservableList<Remark> getRemarkList() {
        final ObservableList<Remark> remarks = this.remarks.stream().map(p -> {
            try {
                return p.toModelType();
            } catch (IllegalValueException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toCollection(FXCollections::observableArrayList));
        return FXCollections.unmodifiableObservableList(remarks);
    }

}
