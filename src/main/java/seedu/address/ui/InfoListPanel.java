package seedu.address.ui;

import java.util.logging.Logger;

import org.fxmisc.easybind.EasyBind;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.ChangeListingUnitEvent;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.address.model.person.ReadOnlyPerson;

/**
 * Panel containing the list of info.
 */
public class InfoListPanel extends UiPart<Region> {
    private static final String FXML = "InfoListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InfoListPanel.class);

    private ObservableList<ReadOnlyPerson> infoList;

    @FXML
    private ListView<PersonCard> infoListView;


    public InfoListPanel(ObservableList<ReadOnlyPerson> infoList) {
        super(FXML);
        this.infoList = infoList;
        setConnections(infoList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<ReadOnlyPerson> infoList) {

        ObservableList<PersonCard> mappedList = EasyBind.map(
                infoList, (person) -> new PersonCard(person, infoList.indexOf(person) + 1));

        infoListView.setItems(mappedList);
        infoListView.setCellFactory(listView -> new PersonListViewCell());
        setEventHandlerForSelectionChangeEvent();

    }


    private void setEventHandlerForSelectionChangeEvent() {
        infoListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        logger.fine("Selection in person list panel changed to : '" + newValue + "'");
                        raise(new PersonPanelSelectionChangedEvent(newValue));
                    }
                });
    }

    /**
     * Scrolls to the {@code PersonCard} at the {@code index} and selects it.
     */
    private void scrollTo(int index) {
        Platform.runLater(() -> {
            infoListView.scrollTo(index);
            infoListView.getSelectionModel().clearAndSelect(index);
        });
    }

    @Subscribe
    private void handleJumpToListRequestEvent(JumpToListRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        scrollTo(event.targetIndex);
    }

    @Subscribe
    private void handleChangeListingUnitEvent(ChangeListingUnitEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        setConnections(infoList);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<PersonCard> {

        @Override
        protected void updateItem(PersonCard person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(person.getRoot());
            }
        }
    }
}