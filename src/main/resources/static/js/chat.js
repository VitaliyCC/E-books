document.addEventListener("DOMContentLoaded", function () {
    const chatMessages = document.getElementById("chat-messages");
    const messageInput = document.getElementById("message-input");
    const sendButton = document.getElementById("send-button");

    sendButton.addEventListener("click", function () {
        const message = messageInput.value;
        if (message.trim() !== "") {
            addMessage("user", message);
            sendMessageToServer(message);
            messageInput.value = "";
        }
    });

    messageInput.addEventListener("keydown", function (event) {
        if (event.key === "Enter") {
            event.preventDefault();
            sendButton.click();
        }
    });

    function addMessage(role, text) {
        const messageDiv = document.createElement("div");
        messageDiv.classList.add(role + "-message");
        messageDiv.textContent = text;
        chatMessages.appendChild(messageDiv);
        chatMessages.scrollTop = chatMessages.scrollHeight;
    }

    function sendMessageToServer(message) {
        const formData = new FormData();
        formData.append("message", message);

        fetch("/send-message", {
            method: "POST",
            body: formData,
        })
            .then(response => response.text())
            .then(data => {
                addMessage("assistant", data);
            })
            .catch(error => {
                console.error("Error sending message:", error);
            });
    }
});
