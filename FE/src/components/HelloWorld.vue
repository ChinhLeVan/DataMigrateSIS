<template>
  <div>
    <h1>Demo Upload File CSV</h1>
    <input
      class="input-button"
      type="file"
      @change="handleFileUpload"
      accept=".csv"
      ref="fileInput"
    />
    <button class="button" @click="uploadFile">Upload</button>
    <button class="button" @click="getData">Get Data</button>
    <button class="button" @click="downloadData" v-if="tableData.length > 0">
      Download file
    </button>
    <p v-if="message">{{ message }}</p>

    <table v-if="tableData.length > 0">
      <thead>
        <tr>
          <th>Id</th>
          <th>Name</th>
          <th>Address</th>
          <th>Age</th>
          <th>Phone</th>
          <th>Email</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in tableData" :key="index">
          <td>{{ item.id }}</td>
          <td>{{ item.name }}</td>
          <td>{{ item.address }}</td>
          <td>{{ item.age }}</td>
          <td>{{ item.phone }}</td>
          <td>{{ item.mail }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import * as XLSX from "xlsx";

export default {
  data() {
    return {
      file: null,
      message: "",
      tableData: [],
    };
  },
  methods: {
    handleFileUpload(event) {
      this.message = "";
      this.file = event.target.files[0];
      this.tableData = [];
    },
    resetFileInput() {
      this.$refs.fileInput.value = null;
    },
    async getData() {
      try {
        const response = await fetch("http://172.20.20.34:8085/user/get-all");
        if (response.ok) {
          this.tableData = await response.json();
        } else {
          this.message = "Failed to fetch data.";
        }
      } catch (error) {
        console.error("Error fetching data:", error);
        this.message = "Error fetching data.";
      }
    },
    async uploadFile() {
      this.resetFileInput();
      if (!this.file || !this.file.name.endsWith(".csv")) {
        this.message = "Please select a CSV file.";
        return;
      }

      const formData = new FormData();
      formData.append("file", this.file);

      try {
        const response = await fetch(
          "http://172.20.20.34:8085/upload-file/csv",
          {
            method: "POST",
            body: formData,
          }
        );

        if (response.ok) {
          this.message = "The file has been uploaded successfully!";
          this.file = null;
        } else {
          this.message = "An error occurred while uploading the file.";
        }
      } catch (error) {
        this.message = "Connection error to the server.";
        console.error(error);
      }
    },
    downloadData() {
      if (this.tableData.length === 0) {
        this.message = "No data available to download.";
        return;
      }

      const headers = ["Id", "Name", "Address", "Age", "Phone", "Email"];

      const cleanedData = this.tableData.map((item) => ({
        Id: item.id,
        Name: item.name,
        Address: item.address,
        Age: item.age,
        Phone: item.phone,
        Email: item.mail,
      }));

      const ws = XLSX.utils.json_to_sheet(cleanedData, { header: headers });
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, "Sheet1");

      XLSX.writeFile(wb, "tableData.xlsx");
    },
  },
};
</script>

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

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

table th,
table td {
  padding: 12px 20px;
  text-align: left;
  font-size: 16px;
  color: #333;
  border: 1px solid #ddd;
}

table th {
  background: linear-gradient(135deg, #ff4d4d, #b30000);
  color: white;
  font-weight: bold;
  text-transform: uppercase;
  box-shadow: 0 4px 10px rgba(255, 77, 77, 0.2);
}

table tr:hover {
  background-color: #f2f2f2;
}

table td {
  background-color: #fff;
}

table tr:nth-child(even) td {
  background-color: #f9f9f9;
}

table td {
  transition: background-color 0.3s ease;
}

table th,
table td {
  border-radius: 8px;
}

table td:hover {
  background-color: #ffebeb;
}
</style>
