variable "access_key" {}
variable "secret_key" {}

provider "aws" {
    access_key = "${var.access_key}"
    secret_key = "${var.secret_key}"
    region = "eu-west-1"
}

resource "aws_key_pair" "deployer-key" {
    key_name = "deployer-key"
    public_key = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQCphlDf9I72YDms37+pz9e42leo4imudMH+U48y1cOo7aXRMXjtq7yhQM1s903A08yjvqSeFfaiEXangUDS6Bvk95CmViUL6H5KvEYQjRldJB3qkGWtuPD4GXQwdA5JIMxv0YTAVczT+GnghuaRydPO/3bXJbsSrWkgXMIe46kz3mpeVAd+sydv0HWYVRiFtekLbrxISde8qM5HWHWFlZICuKwrHkscQeh44XvbtgflRSzzniXYhA6jqUN1bNe8UtWdXl70xWA7NaNSJCfSchGiUk+njxbmXzhwzMHyw3FoYhVyxtxR87FCcYY89/Tu4pBh5kqUTfyadAl2h69NzkL3piKRrIykAfMjTbPgZ4NPPjKwoawaz3RJX8Ys8CWT5nfUWRA4nKyWClc7iZVpfQFiPcqCoH9x9OehAUUfAw8WBQwJdkgfMUJeadVzm/05OTIN6fW34IdJH9H4TPdEIayBulWYA1M7TWSlsypOn48/1loZzOVyYKf84c3U0ukX+XkFHtr0XOCYlg/k3ywUW+0cN5ukl0RYoxZCxmJVYtmoWZo0i5GPgUPONilbISlstnbQl3ks8DesmkcDCh+pksVP8jys71F4FpsVuVj0fuNVjFjp0WAlM6L1KH63NJbUu7cn0oVKyU1ZHV2U9+/t9UOACwV6d0TETyxZ1UUCO8odBQ== ec2-user"
}

resource "aws_instance" "core" {
    ami = "ami-47a23a30"
    instance_type = "t2.micro"
    key_name = "deployer-key"

    connection {
        user = "ubuntu"
        key_file = "deployer" 
    }

    provisioner "remote-exec" {
        inline = ["wget -qO- https://get.docker.com/ | sudo sh", "sudo usermod -aG docker ubuntu"]
    }
}

resource "aws_eip" "core_ip" {
    instance = "${aws_instance.core.id}"
}
