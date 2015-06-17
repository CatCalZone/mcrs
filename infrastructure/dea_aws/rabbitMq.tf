variable "access_key" {}
variable "secret_key" {}
variable "ssh_key_path" {}
variable "ssh_key_name" {
    default = "NOTSET"
}
variable "region" {
    default = "eu-west-1"
}

variable "username" {
    default = "ubuntu"
}

variable "amis" {
    default = {
        eu-west-1    = "ami-47a23a30"
        eu-central-1 = "TODO"
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
    key_name ="${var.ssh_key_name}"
    security_groups = ["${aws_security_group.rabbitMq.name}"]
    tags {
        Name = "theRabbit"
    }
   
    connection {
        user = "${var.username}"
        key_file = "${var.ssh_key_path}"
    }

    provisioner "remote-exec" {
        inline = ["wget -qO- https://get.docker.com/ | sudo sh",
                  "sudo usermod -aG docker ubuntu",
                  "sudo docker run -d -e RABBITMQ_NODENAME=my-rabbit -p 5672:5672 -p 15672:15672  --name theRabbit rabbitmq:3-management"]
    }

}

resource "aws_eip" "ip" {
    instance = "${aws_instance.example.id}"
}

output "ip" {
    value = "${aws_eip.ip.public_ip}"
}

resource "aws_security_group" "rabbitMq" {
  name = "rabbitMq"
  description = "Allow all rabbitMQ management console"

  ingress {
      from_port = 15672
      to_port = 15672
      protocol = "tcp"
      cidr_blocks = ["0.0.0.0/0"]
  }

   ingress {
      from_port = 22
      to_port = 22
      protocol = "tcp"
      cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
      from_port = 0
      to_port = 0
      protocol = "-1"
      cidr_blocks = ["0.0.0.0/0"]
  }
 
}
