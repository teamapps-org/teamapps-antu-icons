/*-
 * ========================LICENSE_START=================================
 * TeamApps Antu Icon Provider
 * ---
 * Copyright (C) 2014 - 2020 TeamApps.org
 * ---
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */
package org.teamapps.icon.antu;

import org.teamapps.common.format.Color;
import org.teamapps.server.jetty.embedded.TeamAppsJettyEmbeddedServer;
import org.teamapps.ux.component.Component;
import org.teamapps.ux.component.field.TemplateField;
import org.teamapps.ux.component.field.TextField;
import org.teamapps.ux.component.field.combobox.ComboBox;
import org.teamapps.ux.component.flexcontainer.VerticalLayout;
import org.teamapps.ux.component.form.ResponsiveForm;
import org.teamapps.ux.component.form.ResponsiveFormLayout;
import org.teamapps.ux.component.infiniteitemview.InfiniteItemView2;
import org.teamapps.ux.component.infiniteitemview.ListInfiniteItemViewModel;
import org.teamapps.ux.component.notification.Notification;
import org.teamapps.ux.component.notification.NotificationPosition;
import org.teamapps.ux.component.panel.Panel;
import org.teamapps.ux.component.rootpanel.RootPanel;
import org.teamapps.ux.component.template.BaseTemplate;
import org.teamapps.ux.component.template.BaseTemplateRecord;
import org.teamapps.ux.session.SessionContext;
import org.teamapps.webcontroller.WebController;

import java.util.stream.Collectors;

public class AntuIconBrowser {

    private SessionContext sessionContext;
    private AntuIconStyle iconStyle;
    private final ListInfiniteItemViewModel<AntuIcon> iconViewModel = new ListInfiniteItemViewModel<>(AntuIcon.getIcons());

    public AntuIconBrowser(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public Component getUI() {
        Panel panel = new Panel();
        Component iconFinder = createIconFinder();
        panel.setContent(iconFinder);
        panel.setTitle("Icon Viewer");
        panel.setIcon(AntuIcon.APP_GCSTAR_48);
        return panel;
    }

    protected Component createIconFinder() {
        Panel iconViewComponent = createIconViewer();

        VerticalLayout verticalLayout = new VerticalLayout();
        // New Component: ResponsiveForm
        ResponsiveForm responsiveForm = new ResponsiveForm<>(100,200,0);
        verticalLayout.addComponent(responsiveForm);

        ResponsiveFormLayout layout = responsiveForm.addResponsiveFormLayout(400);

        // Icon Search
        layout.addSection(AntuIcon.ACTION_VIEW_FILTER_24,"Filter Icons");
        TextField searchField = new TextField();
        layout.addLabelAndField(AntuIcon.ACTION_SEARCH_24, "Icon Name", searchField);
        searchField.setEmptyText("Search...");
        searchField.onTextInput.addListener(s ->iconViewModel.setRecords(AntuIcon.getIcons().stream().filter(icon -> s == null || icon.getIconId().contains(s.toUpperCase())).collect(Collectors.toList())));
        verticalLayout.addComponent(searchField);

        // Style Selector
        ComboBox<AntuIconStyle> styleSelector = ComboBox.createForList(AntuIconStyle.getStyles());
        styleSelector.setTemplate(BaseTemplate.LIST_ITEM_MEDIUM_ICON_SINGLE_LINE);
        styleSelector.setPropertyExtractor((style, propertyName) -> {
            switch (propertyName) {
                case BaseTemplate.PROPERTY_ICON:
                    return AntuIcon.ACTION_DRAW_BRUSH_24;
                case BaseTemplate.PROPERTY_CAPTION:
                    return style.getStyleId();
            }
            return null;
        });
        styleSelector.setRecordToStringFunction(AntuIconStyle::getStyleId);
        styleSelector.setValue(AntuIconStyle.LIGHT);
        styleSelector.setShowClearButton(false);
        styleSelector.onValueChanged.addListener(style -> {
            iconStyle = style;
            iconViewModel.onAllDataChanged.fire();
            if (style.equals(AntuIconStyle.DARK)) {
                iconViewComponent.setBodyBackgroundColor(Color.BLACK.withAlpha(0.96f));
            } else {
                iconViewComponent.setBodyBackgroundColor(Color.WHITE.withAlpha(0.96f));
            }
        });
        layout.addLabelAndField(AntuIcon.ACTION_DRAW_BRUSH_24, "Icon Style", styleSelector);
        verticalLayout.addComponentFillRemaining(iconViewComponent);
        return verticalLayout;
    }

    public Panel createIconViewer() {
        InfiniteItemView2<AntuIcon> iconView = new InfiniteItemView2<>();
        iconView.setItemTemplate(BaseTemplate.LIST_ITEM_LARGE_ICON_SINGLE_LINE);
        iconView.setItemHeight(50);
        iconView.setItemWidth(300);
        iconView.setItemPropertyExtractor((antuIcon, propertyName) -> {
            switch (propertyName) {
                case BaseTemplate.PROPERTY_ICON:
                    return antuIcon.withStyle(iconStyle);
                case BaseTemplate.PROPERTY_CAPTION:
                    return antuIcon.getIconId();
                default:
                    return null;
            }
        });
        AntuIconStyle iconStyle = AntuIconStyle.LIGHT;
        iconView.setModel(iconViewModel);
        Panel panel = new Panel(null, "Icons");
        panel.setContent(iconView);
        panel.setBodyBackgroundColor(Color.WHITE.withAlpha(0.96f));
        iconView.onItemClicked.addListener(iconItemClickedEventData -> {

            // Custom Notification with VERY LARGE ICON
            TemplateField<BaseTemplateRecord<Void>> templateField = new TemplateField<>(BaseTemplate.LIST_ITEM_EXTRA_VERY_LARGE_ICON_TWO_LINES);
            AntuIcon icon = iconItemClickedEventData.getRecord();
            templateField.setValue(new BaseTemplateRecord<>(icon, "AntuIcon." + icon.getIconId(), icon.getIconPath()+".svg"));
            Notification iconNotification = new Notification();
            iconNotification.setContent(templateField);
            iconNotification.setShowProgressBar(false);
            iconNotification.setDisplayTimeInMillis(5000);
            sessionContext.showNotification(iconNotification, NotificationPosition.TOP_RIGHT);
        });
        return panel;
    }

    // main method to launch the IconBrowser standalone
    public static void main(String[] args) throws Exception {
        WebController controller = sessionContext -> {
            RootPanel rootPanel = new RootPanel();
            sessionContext.addRootPanel(null, rootPanel);
            Component antuIconBrowser = new AntuIconBrowser(sessionContext).getUI();
            rootPanel.setContent(antuIconBrowser);
        };
        new TeamAppsJettyEmbeddedServer(controller, 8082).start();
    }

}
