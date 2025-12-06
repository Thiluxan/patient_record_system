import React from "react";

const PatientForm = ({ mode, patient, onChange, onSubmit }) => {
  const isCreate = mode === "create";

  return (
    <form className="patient-form" onSubmit={onSubmit}>
      <div className="form-row">
        <label>
          First Name
          <input
            name="firstName"
            value={patient.firstName}
            onChange={onChange}
            required
          />
        </label>

        <label>
          Last Name
          <input
            name="lastName"
            value={patient.lastName}
            onChange={onChange}
            required
          />
        </label>
      </div>

      <div className="form-row">
        <label>
          Address
          <input
            name="address"
            value={patient.address}
            onChange={onChange}
          />
        </label>
      </div>

      <div className="form-row">
        <label>
          City
          <input
            name="city"
            value={patient.city}
            onChange={onChange}
          />
        </label>

        <label>
          State
          <input
            name="state"
            value={patient.state}
            onChange={onChange}
          />
        </label>

        <label>
          Zip Code
          <input
            name="zipCode"
            value={patient.zipCode}
            onChange={onChange}
          />
        </label>
      </div>

      <div className="form-row">
        <label>
          Phone Number
          <input
            name="phoneNumber"
            value={patient.phoneNumber}
            onChange={onChange}
          />
        </label>

        <label>
          Email
          <input
            type="email"
            name="email"
            value={patient.email}
            onChange={onChange}
            required
          />
        </label>
      </div>

      <button className="btn btn-primary">
        {isCreate ? "Create Patient" : "Save"}
      </button>
    </form>
  );
};

export default PatientForm;