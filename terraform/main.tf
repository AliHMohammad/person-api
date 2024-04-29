


resource "azurerm_service_plan" "this" {
  name                = "${var.name}-springboot-service-plan"
  resource_group_name = azurerm_resource_group.this.name
  location            = azurerm_resource_group.this.location
  os_type             = "Linux"
  sku_name            = "F1"
}

resource "azurerm_resource_group" "this" {
  location = "North Europe"
  name     = "${var.name}-springboot-resource-grp"
}

resource "azurerm_linux_web_app" "this" {
  name                = "${var.name}-springboot-personapi-app"
  location            = azurerm_service_plan.this.location
  resource_group_name = azurerm_resource_group.this.name
  service_plan_id     = azurerm_service_plan.this.id

  app_settings = {
    JDBC_DATABASE_URL = var.JDBC-URL
    JDBC_USERNAME     = var.DB-USER
    JDBC_PASSWORD     = var.DB-PASSWORD
  }

  site_config {
    application_stack {
      java_server         = "JAVA"
      java_server_version = "java17"
      java_version        = 17
    }
    always_on = false
  }
}

resource "azurerm_app_service_source_control" "this" {
  app_id   = azurerm_linux_web_app.this.id
  repo_url = "https://github.com/AliHMohammad/person-api"
  branch   = "main"
}