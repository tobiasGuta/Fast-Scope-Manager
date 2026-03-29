# Fast Scope Manager

This is a Burp Suite extension that provides a quick and easy way to manage your target scope directly from the Proxy history.

## Features

- Adds a "Fast Add Host to Scope (HTTP/HTTPS)" option to the right-click context menu in the Proxy tool.
- Adds a "Fast Exclude Host from Scope (HTTP/HTTPS)" option to the right-click context menu in the Proxy tool.
- When you select one or more requests, you can use these options to add or remove the corresponding hosts from your scope.
- The extension ensures that both the HTTP and HTTPS versions of the host's root URL are added to or removed from the scope.

## How to Use

1.  Navigate to the **Proxy** tab in Burp Suite and then go to the **HTTP history** sub-tab.
2.  Select one or more HTTP requests in the history table.
3.  Right-click on the selected request(s).
4.  In the context menu, you will find two new options:
    -   **Fast Add Host to Scope (HTTP/HTTPS)**
    -   **Fast Exclude Host from Scope (HTTP/HTTPS)**
5.  Clicking one of these options will either add or remove all selected hosts from your target scope.
