package com.example.project.view;

import com.example.project.entities.FolderColorEnum;
import com.example.project.errors.NotFoundException;
import com.example.project.models.FolderDTO;
import com.example.project.service.FolderService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.Enumerated;

@Route
public class FolderView extends VerticalLayout {

    @Autowired
    private FolderService service;
    private VerticalLayout form = new VerticalLayout();
    private Binder<FolderDTO> binder;
    private TextField name;
    private TextField description;
    private TextField color;
    private TextField parentId;
    //private TextField parent;
    private FolderDTO selectedDto = new FolderDTO();

    @PostConstruct
    public void init() {
        Grid<FolderDTO> grid = new Grid<>();
        grid.setItems(service.findAll());
        grid.addColumn(FolderDTO::getId).setHeader("ID");
        grid.addColumn(FolderDTO::getName).setHeader("Name");
        grid.addColumn(FolderDTO::getDescription).setHeader("Description");
        grid.addColumn(FolderDTO::getColor).setHeader("Color");
        grid.addColumn(FolderDTO::getParentId).setHeader("Parent Folder ID");


        createButtonBarAndSelection(grid);
        add(grid);

        createForm(grid);

    }

    private void createForm(Grid<FolderDTO> folderDTOGrid) {
        form.setVisible(false);
        binder = new Binder<>(FolderDTO.class);
        name = new TextField();
        description = new TextField();
        color = new TextField();
        //color.setItems(FolderColorEnum.values());
        /*parent = new TextField();
        binder.forField(parent)
                .withNullRepresentation("")
                .withConverter( new StringToLongConverter("Enter a number")).bind("parentId");*/
        parentId = new TextField();
        binder.forField(parentId)
                .withNullRepresentation("")
                .withConverter( new StringToLongConverter("Enter a number")).bind("parentId");


        form.add(new Text("Name"), name);
        form.add(new Text("Description"), description);
        form.add(new Text("Color"), color);
        form.add(new Text("Parent ID"), parentId);

        Button button = new Button();
        button.setText("Save");
        button.addClickListener(buttonClickEvent -> {
            try {
                if (selectedDto.getId() != null) {
                    service.update(selectedDto);
                } else {
                    service.save(selectedDto);

                }
                folderDTOGrid.setItems(service.findAll());
                selectedDto = new FolderDTO();
                form.setVisible(false);
            } catch (NotFoundException e) {
                e.printStackTrace();
                Notification.show("Delete error");
            }
        });
        binder.bindInstanceFields(this);
        binder.setBean(selectedDto);

        form.add(button);
        add(form);
    }

    private void createButtonBarAndSelection(Grid<FolderDTO> grid) {
        HorizontalLayout layout = new HorizontalLayout();

        Button createButton = new Button();
        createButton.setText("Add");
        createButton.addClickListener(buttonClickEvent -> {
            selectedDto = new FolderDTO();
            binder.setBean(selectedDto);
            form.setVisible(true);
        });
        layout.add(createButton);

        Button editButton = new Button();
        editButton.setText("Edit");
        editButton.addClickListener(buttonClickEvent -> {
            binder.setBean(selectedDto);
            form.setVisible(true);
        });
        editButton.setVisible(false);
        layout.add(editButton);

        Button deleteButton = new Button();
        deleteButton.setText("Delete");
        deleteButton.addClickListener(buttonClickEvent -> {
            try {
                service.delete(selectedDto.getId());
                grid.setItems(service.findAll());
            } catch (NotFoundException e) {
                e.printStackTrace();
                Notification.show("Delete error");
            }
        });
        deleteButton.setVisible(false);
        layout.add(deleteButton);

        add(layout);

        grid.asSingleSelect().addValueChangeListener(event -> {
            selectedDto = event.getValue();
            deleteButton.setVisible(selectedDto != null);
            editButton.setVisible(selectedDto != null);
            binder.setBean(selectedDto);
        });

    }
}
