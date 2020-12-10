package helsinki.config.personnel;

import static helsinki.common.LayoutComposer.*;
import static helsinki.common.StandardActionsStyles.MASTER_CANCEL_ACTION_LONG_DESC;
import static helsinki.common.StandardActionsStyles.MASTER_CANCEL_ACTION_SHORT_DESC;
import static helsinki.common.StandardActionsStyles.MASTER_SAVE_ACTION_LONG_DESC;
import static helsinki.common.StandardActionsStyles.MASTER_SAVE_ACTION_SHORT_DESC;

import static ua.com.fielden.platform.web.centre.api.context.impl.EntityCentreContextSelector.context;
import static ua.com.fielden.platform.web.layout.api.impl.LayoutBuilder.cell;

import java.util.Optional;

import com.google.inject.Injector;

import helsinki.common.StandardActions;
import helsinki.main.menu.personnel.MiPerson;
import helsinki.personnel.Person;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.centre.api.EntityCentreConfig;
import ua.com.fielden.platform.web.centre.api.actions.EntityActionConfig;
import ua.com.fielden.platform.web.centre.api.impl.EntityCentreBuilder;
import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.action.CentreConfigurationWebUiConfig.CentreConfigActions;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import ua.com.fielden.platform.web.view.master.api.IMaster;
import ua.com.fielden.platform.web.view.master.api.actions.MasterActions;
import ua.com.fielden.platform.web.view.master.api.compound.Compound;
import ua.com.fielden.platform.web.view.master.api.impl.SimpleMasterBuilder;

/**
 * {@link Person} Web UI configuration.
 *
 * @author Generated
 *
 */
public class PersonWebUiConfig {

    private final Injector injector;
    
    public final EntityCentre<Person> centre;
    public final EntityMaster<Person> master;

    public static PersonWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new PersonWebUiConfig(injector, builder);
    }

    private PersonWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        this.injector = injector;

        centre = createPersonCentre(builder);
        builder.register(centre);

        master = createPersonMaster();
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link Person}.
     *
     * @return
     */
    private EntityCentre<Person> createPersonCentre(final IWebUiBuilder builder) {
        final String layout = cell(
                cell(cell().repeat(2).layoutForEach(CELL_LAYOUT).withGapBetweenCells(MARGIN))
               .cell(cell().repeat(2).layoutForEach(CELL_LAYOUT).withGapBetweenCells(MARGIN))
               .cell(cell().repeat(2).layoutForEach(CELL_LAYOUT).withGapBetweenCells(MARGIN)),
               PADDING_LAYOUT).toString();

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(Person.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(Person.class);
        builder.registerOpenMasterAction(Person.class, standardEditAction);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Person.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(Person.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();

        final EntityCentreConfig<Person> ecc = EntityCentreBuilder.centreFor(Person.class)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                // row 1
                .addCrit("this").asMulti().autocompleter(Person.class).also()
                .addCrit("desc").asMulti().text().also()
                // row 2
                .addCrit("aSupervisor").asMulti().autocompleter(Person.class).also()
                .addCrit("supervisor").asMulti().bool().also()
                // row 3
                .addCrit("employeeNo").asMulti().text().also()
                .addCrit("title").asMulti().text()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .addProp("this").order(1).asc().minWidth(50)
                    .withSummary("total_count_", "COUNT(SELF)", "Count:The total number of matching Person.")
                    .withAction(standardEditAction).also()
                .addProp("desc").minWidth(200).also()
                .addProp("employeeNo").minWidth(70).also()
                .addProp("title").minWidth(200).also()
                .addProp("aSupervisor").minWidth(70).also()
                .addProp("supervisor").minWidth(70).also()
                .addProp("phone").minWidth(70).also()
                .addProp("mobile").minWidth(70).also()
                .addProp("email").minWidth(70)
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiPerson.class, MiPerson.class.getSimpleName(), ecc, injector, null);
    }

    private EntityMaster<Person> createPersonMaster() {
        final String layout = cell(
                cell(cell().repeat(2).layoutForEach(CELL_LAYOUT).withGapBetweenCells(MARGIN))
               .cell(cell().layoutForEach(CELL_LAYOUT).withGapBetweenCells(MARGIN))
               .cell(cell().repeat(2).layoutForEach(CELL_LAYOUT).withGapBetweenCells(MARGIN))
               .cell(cell().repeat(2).layoutForEach(CELL_LAYOUT).withGapBetweenCells(MARGIN))
               .cell(cell().repeat(2).layoutForEach(CELL_LAYOUT).withGapBetweenCells(MARGIN))
               .cell(cell().repeat(2).layoutForEach(CELL_LAYOUT).withGapBetweenCells(MARGIN)),
               PADDING_LAYOUT).toString();

        final IMaster<Person> masterConfig = new SimpleMasterBuilder<Person>().forEntity(Person.class)
                // row 1
                .addProp("initials").asSinglelineText().also()
                .addProp("active").asCheckbox().also()
                // row 2
                .addProp("desc").asMultilineText().also()
                // row 3
                .addProp("employeeNo").asSinglelineText().also()
                .addProp("title").asSinglelineText().also()
                // row 4
                .addProp("aSupervisor").asAutocompleter().also()
                .addProp("supervisor").asCheckbox().also()
                // row 5
                .addProp("phone").asSinglelineText().also()
                .addProp("mobile").asSinglelineText().also()
                // row 6
                .addProp("email").asSinglelineText().also()
                .addProp("user").asAutocompleter().also()
                .addAction(MasterActions.REFRESH).shortDesc(MASTER_CANCEL_ACTION_SHORT_DESC).longDesc(MASTER_CANCEL_ACTION_LONG_DESC)
                .addAction(MasterActions.SAVE).shortDesc(MASTER_SAVE_ACTION_SHORT_DESC).longDesc(MASTER_SAVE_ACTION_LONG_DESC)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .done();

        return new EntityMaster<>(Person.class, masterConfig, injector);
    }
}