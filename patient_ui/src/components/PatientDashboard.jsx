import React, { useEffect, useState } from "react";
import {
  fetchPatients,
  createPatient,
  updatePatient,
  deletePatient,
} from "../services/patientService";
import PatientForm from "./PatientForm";
import PatientTable from "./PatientTable";

const EMPTY_PATIENT = {
  firstName: "",
  lastName: "",
  address: "",
  city: "",
  state: "",
  zipCode: "",
  phoneNumber: "",
  email: "",
};

const PatientDashboard = () => {
  const [patients, setPatients] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const [creatingPatient, setCreatingPatient] = useState(EMPTY_PATIENT);

  const [editingId, setEditingId] = useState(null);
  const [editingRow, setEditingRow] = useState(EMPTY_PATIENT);

  const loadPatients = async () => {
    setLoading(true);
    setError("");
    try {
      const data = await fetchPatients();
      setPatients(data || []);
    } catch (err) {
      setError(err.message || "Failed to load patients");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadPatients();
  }, []);

  const handleCreateChange = (e) => {
    const { name, value } = e.target;
    setCreatingPatient((prev) => ({ ...prev, [name]: value }));
  };

  const handleCreateSubmit = async (e) => {
    e.preventDefault();
    setError("");
    try {
      await createPatient(creatingPatient);
      setCreatingPatient(EMPTY_PATIENT);
      loadPatients();
    } catch (err) {
      setError(err.message || "Failed to create patient");
    }
  };

  const startEdit = (patient) => {
    setEditingId(patient.id);
    setEditingRow({
      firstName: patient.firstName || "",
      lastName: patient.lastName || "",
      address: patient.address || "",
      city: patient.city || "",
      state: patient.state || "",
      zipCode: patient.zipCode || "",
      phoneNumber: patient.phoneNumber || "",
      email: patient.email || "",
    });
  };

  const cancelEdit = () => {
    setEditingId(null);
    setEditingRow(EMPTY_PATIENT);
  };

  const handleEditChange = (e) => {
    const { name, value } = e.target;
    setEditingRow((prev) => ({ ...prev, [name]: value }));
  };

  const saveEdit = async (id) => {
    setError("");
    try {
      await updatePatient(id, editingRow);
      setEditingId(null);
      setEditingRow(EMPTY_PATIENT);
      loadPatients();
    } catch (err) {
      setError(err.message || "Failed to update patient");
    }
  };

  const handleDelete = async (id) => {
    setError("");
    if (!window.confirm("Are you sure you want to delete this patient?")) return;

    try {
      await deletePatient(id);
      setPatients((prev) => prev.filter((p) => p.id !== id));
    } catch (err) {
      setError(err.message || "Failed to delete patient");
    }
  };

  return (
    <div className="dashboard">
      <h1>Patient Dashboard</h1>

      {error && <div className="alert alert-error">{error}</div>}
      {loading && <div className="alert alert-info">Loading patients...</div>}

      <section className="card">
        <h2>Add New Patient</h2>
        <PatientForm
          mode="create"
          patient={creatingPatient}
          onChange={handleCreateChange}
          onSubmit={handleCreateSubmit}
        />
      </section>

      <section className="card">
        <h2>Existing Patients</h2>
        <PatientTable
          patients={patients}
          editingId={editingId}
          editingRow={editingRow}
          onStartEdit={startEdit}
          onCancelEdit={cancelEdit}
          onEditChange={handleEditChange}
          onSaveEdit={saveEdit}
          onDelete={handleDelete}
        />
      </section>
    </div>
  );
};

export default PatientDashboard;
