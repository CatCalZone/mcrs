provider "aws" {
    region = "eu-west-1"
}

variable "ecs_cluster" {}

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
