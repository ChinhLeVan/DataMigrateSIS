<template>
  <div>
    <h1>Upload CSV File System Demo</h1>
    <input type="file" @change="handleFileUpload" accept=".csv" />
    <button @click="uploadFile">Upload</button>
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
        this.message = "Vui lòng chọn một file CSV.";
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
          this.message = "File đã được upload thành công!";
        } else {
          this.message = "Có lỗi xảy ra khi upload file.";
        }
      } catch (error) {
        this.message = "Lỗi kết nối đến server.";
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
</style>
