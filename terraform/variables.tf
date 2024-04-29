


variable "name" {
  description = "prefix variable used for naming resources"
  default     = "almo-kea-v1"
  type        = string
}

variable "JDBC-URL" {
  type      = string
  sensitive = true

}

variable "DB-USER" {
  type      = string
  sensitive = true
}

variable "DB-PASSWORD" {
  type      = string
  sensitive = true
}