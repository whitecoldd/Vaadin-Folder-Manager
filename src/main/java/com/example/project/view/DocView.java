package com.example.project.view;

import com.example.project.errors.NotFoundException;
import com.example.project.models.DocDTO;
import com.example.project.service.DocService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
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
public class DocView extends VerticalLayout {

    @Autowired
    private DocService service;
    private VerticalLayout form = new VerticalLayout();
    private Binder<DocDTO> binder;
    private TextField name;
    private TextField description;
    private TextField url;
    private TextField doctype;
    private TextField folderId;
    private DocDTO selectedDto = new DocDTO();

    @PostConstruct
    @Enumerated
    public void init() {
        Grid<DocDTO> grid = new Grid<>();
        grid.setItems(service.findAll());
        grid.addColumn(DocDTO::getId).setHeader("ID");
        grid.addColumn(DocDTO::getName).setHeader("Name");
        grid.addColumn(DocDTO::getDescription).setHeader("Description");
        grid.addColumn(DocDTO::getUrl).setHeader("Url link");
        grid.addColumn(DocDTO::getDocType).setHeader("Mimetype");
        grid.addColumn(DocDTO::getFolderId).setHeader("Parent Folder ID");


        createButtonBarAndSelection(grid);
        add(grid);

        createForm(grid);

    }

    private void createForm(Grid<DocDTO> docDTOGrid) {
        form.setVisible(false);
        binder = new Binder<>(DocDTO.class);
        name = new TextField();
        description = new TextField();
        url = new TextField();
        doctype = new TextField();
        folderId = new TextField();
        binder.forField(folderId)
                .withNullRepresentation("")
                .withConverter( new StringToLongConverter("Enter a number")).bind("folderId");


        form.add(new Text("Name"), name);
        form.add(new Text("Description"), description);
        form.add(new Text("Url Link"), url);
        form.add(new Text("MimeType"), doctype);
        form.add(new Text("Parent ID"), folderId);

        Button button = new Button();
        button.setText("Save");
        button.addClickListener(buttonClickEvent -> {
            try {
                if (selectedDto.getId() != null) {
                    service.update(selectedDto);
                } else {
                    service.save(selectedDto);

                }
                docDTOGrid.setItems(service.findAll());
                selectedDto = new DocDTO();
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

    private void createButtonBarAndSelection(Grid<DocDTO> grid) {
        HorizontalLayout layout = new HorizontalLayout();

        Button createButton = new Button();
        createButton.setText("Add");
        createButton.addClickListener(buttonClickEvent -> {
            selectedDto = new DocDTO();
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
