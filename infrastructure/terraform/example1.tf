variable "access_key" {}
variable "secret_key" {}
variable "region" {
    default = "us-east-1"
}

variable "amis" {
    default = {
        us-east-1    = "ami-1ecae776"
        eu-central-1 = "ami-a8221fb5"
    }
}

provider "aws" {
    access_key = "${var.access_key}"
    secret_key = "${var.secret_key}"
    region = "${var.region}"
}

resource "aws_instance" "example" {
    ami = "${lookup(var.amis, var.region)}"
    instance_type = "t2.micro"

}

resource "aws_eip" "ip" {
    instance = "${aws_instance.example.id}"
}

output "ip" {
    value = "${aws_eip.ip.public_ip}"
}