variable "aws_region" {
  type = "string"
  description = "The region in which the RabbitMQ service should run"
  default = "eu-west-1"
}

variable "ecs_cluster" {
  type = "string"
  description = "The cluster in which the RabbitMQ service will be run (must exist)"
}

provider "aws" {
    region = "${var.aws_region}"
}

resource "aws_ecs_task_definition" "therabbit_taskdef" {
  family = "therabbit"
  container_definitions = "${file("therabbit.json")}"
}

resource "aws_ecs_service" "therabbit_service" {
  name = "therabbit-service"
  cluster = "${var.ecs_cluster}"
  task_definition = "${aws_ecs_task_definition.therabbit_taskdef.arn}"
  desired_count = 1
}
