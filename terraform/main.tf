
resource "random_integer" "this" {
  min = 10000
  max = 99999
}

resource "azurerm_service_plan" "this" {
  name                = "${var.name}-service-plan-${random_integer.this.result}"
  resource_group_name = azurerm_resource_group.this.name
  location            = azurerm_resource_group.this.location
  os_type             = "Linux"
  sku_name            = "F1"
}

resource "azurerm_resource_group" "this" {
  location = "North Europe"
  name     = "${var.name}-resource-grp-${random_integer.this.result}"
}

resource "azurerm_linux_web_app" "this" {
  name                = "${var.name}-personapi-app-${random_integer.this.result}"
  location            = azurerm_service_plan.this.location
  resource_group_name = azurerm_resource_group.this.name
  service_plan_id     = azurerm_service_plan.this.id
  https_only          = true

  site_config {
    application_stack {
      java_server         = "JAVA"
      java_server_version = "java17"
      java_version        = 17
    }
    always_on = false
  }

  app_settings = {
    JDBC_DATABASE_URL = var.JDBC-URL
    JDBC_USERNAME     = var.DB-USER
    JDBC_PASSWORD     = var.DB-PASSWORD
  }


}

resource "azurerm_app_service_source_control" "this" {
  app_id   = azurerm_linux_web_app.this.id
  repo_url = "https://github.com/AliHMohammad/person-api"
  branch   = "main"

  github_action_configuration {
    code_configuration {
      runtime_stack   = "spring"
      runtime_version = "17"
    }
    generate_workflow_file = true
  }
}