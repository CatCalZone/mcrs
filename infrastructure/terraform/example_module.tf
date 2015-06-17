variable "access_key" {}
variable "secret_key" {}
variable "region" {
    default = "us-east-1"
}

module "consul" {
    source = "github.com/hashicorp/consul/terraform/aws"

    key_name = "dea"
    key_path = "W:\home\aws\dea.pem"
    region = "${var.region}"
    servers = "3"
}