variable "aws_region" {
  type = "string"
  description = "The region in which the Consul service should run"
  default = "eu-west-1"
}

variable "ecs_cluster" {
  type = "string"
  description = "The cluster in which the Consul service will be run (must exist)"
}

provider "aws" {
    region = "${var.aws_region}"
}

resource "aws_ecs_task_definition" "consul_taskdef" {
  family = "consul"
  container_definitions = "${file("consul.json")}"
}

resource "aws_ecs_service" "consul_service" {
  name = "consul-service"
  cluster = "${var.ecs_cluster}"
  task_definition = "${aws_ecs_task_definition.consul_taskdef.arn}"
  desired_count = 1
}
