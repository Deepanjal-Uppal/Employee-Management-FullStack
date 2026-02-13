import React, { useState, useEffect } from "react";
import EmployeeForm from "./components/EmployeeForm";
import EmployeeList from "./components/EmployeeList";
import { getEmployees } from "./services/EmployeeService";
import "./App.css";

function App() {

  const [employees, setEmployees] = useState([]);
  const [editingEmployee, setEditingEmployee] = useState(null);

  const fetchEmployees = async () => {
    const data = await getEmployees();
    setEmployees(data);
  };

  useEffect(() => {
    fetchEmployees();
  }, []);

  return (
    <div className="app-container">
      <h1>Employee Management System</h1>

      <div className="card">
        <EmployeeForm
          fetchEmployees={fetchEmployees}
          editingEmployee={editingEmployee}
          setEditingEmployee={setEditingEmployee}
        />
      </div>

      <div className="card">
        <EmployeeList
          employees={employees}
          fetchEmployees={fetchEmployees}
          setEditingEmployee={setEditingEmployee}
        />
      </div>
    </div>
  );
}

export default App;
