

output "created_at" {
  value = timestamp()
}

output "hostname_url" {
  value = azurerm_linux_web_app.this.default_hostname
}