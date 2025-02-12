<template>
  <div>
    <h1>Demo Upload File CSV</h1>
    <input
      class="input-button"
      type="file"
      @change="handleFileUpload"
      accept=".csv"
    />
    <button class="button" @click="uploadFile">Upload</button>
    <p v-if="message">{{ message }}</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      file: null,
      message: "",
    };
  },
  methods: {
    handleFileUpload(event) {
      this.file = event.target.files[0]; // Lấy file từ input
    },
    async uploadFile() {
      if (!this.file || !this.file.name.endsWith(".csv")) {
        this.message = "Please select a CSV file.";
        return;
      }

      const formData = new FormData();
      formData.append("file", this.file); // Thêm file vào FormData

      try {
        const response = await fetch("http://10.1.50.85:8080/upload-file/csv", {
          method: "POST",
          body: formData,
        });

        if (response.ok) {
          this.message = "The file has been uploaded successfully!";
        } else {
          this.message = "An error occurred while uploading the file.";
        }
      } catch (error) {
        this.message = "Connection error to the server.";
        console.error(error);
      }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.button {
  display: inline-block;
  padding: 15px 24px;
  margin-left: 10px;
  font-size: 16px;
  font-weight: bold;
  text-align: center;
  text-transform: uppercase;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
  background: linear-gradient(135deg, #ff4d4d, #b30000);
  color: white;
  box-shadow: 0 4px 10px rgba(255, 77, 77, 0.2);
}

.button:hover {
  background: linear-gradient(135deg, #b30000, #800000);
  box-shadow: 0 6px 15px rgba(255, 77, 77, 0.3);
}

.button:active {
  transform: scale(0.95);
  box-shadow: 0 3px 8px rgba(255, 77, 77, 0.2);
}
.input-button {
  display: inline-block;
  padding: 12px 24px;
  font-size: 16px;
  font-weight: bold;
  text-align: center;
  text-transform: uppercase;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
  background: linear-gradient(135deg, #ff4d4d, #b30000);
  color: white;
  box-shadow: 0 4px 10px rgba(255, 77, 77, 0.2);
}

.input-button:hover {
  background: linear-gradient(135deg, #b30000, #800000);
  box-shadow: 0 6px 15px rgba(255, 77, 77, 0.3);
}

.input-button:active {
  transform: scale(0.95);
  box-shadow: 0 3px 8px rgba(255, 77, 77, 0.2);
}
</style>
