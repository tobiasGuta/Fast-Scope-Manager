import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.core.ToolType;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.ui.contextmenu.ContextMenuEvent;
import burp.api.montoya.ui.contextmenu.ContextMenuItemsProvider;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CustomScopeManager implements BurpExtension {
    private MontoyaApi api;

    @Override
    public void initialize(MontoyaApi api) {
        this.api = api;
        api.extension().setName("Fast Scope Manager");

        // Register the right-click menu provider
        api.userInterface().registerContextMenuItemsProvider(new ScopeMenu());
    }

    private class ScopeMenu implements ContextMenuItemsProvider {
        @Override
        public List<Component> provideMenuItems(ContextMenuEvent event) {
            if (event.isFromTool(ToolType.PROXY)) {
                List<Component> menuList = new ArrayList<>();
                List<HttpRequestResponse> selectedMessages = event.selectedRequestResponses();

                if (!selectedMessages.isEmpty()) {
                    JMenuItem includeItem = new JMenuItem("Fast Add Host to Scope (HTTP/HTTPS)");
                    includeItem.addActionListener(e -> {
                        for (HttpRequestResponse msg : selectedMessages) {
                            // Extract just the host (e.g., "www.google.com")
                            String host = msg.request().httpService().host();

                            // Construct root URLs for both protocols
                            String httpUrl = "http://" + host + "/";
                            String httpsUrl = "https://" + host + "/";

                            // The API translates these root URLs to Port 80/443 and File ^/.* api.scope().includeInScope(httpUrl);
                            api.scope().includeInScope(httpsUrl);
                        }
                    });

                    JMenuItem excludeItem = new JMenuItem("Fast Exclude Host from Scope (HTTP/HTTPS)");
                    excludeItem.addActionListener(e -> {
                        for (HttpRequestResponse msg : selectedMessages) {
                            String host = msg.request().httpService().host();

                            String httpUrl = "http://" + host + "/";
                            String httpsUrl = "https://" + host + "/";

                            api.scope().excludeFromScope(httpUrl);
                            api.scope().excludeFromScope(httpsUrl);
                        }
                    });

                    menuList.add(includeItem);
                    menuList.add(excludeItem);
                    return menuList;
                }
            }
            return null;
        }
    }
}