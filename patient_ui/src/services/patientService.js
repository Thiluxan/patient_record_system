const handleResponse = async (res) => {
    if (!res.ok) {
      const text = await res.text();
      throw new Error(
        `Request failed (${res.status}): ${text || res.statusText || "Unknown error"}`
      );
    }
    if (res.status === 204) return null;
    return res.json();
  };
  
  export const fetchPatients = async () => {
    const res = await fetch("/patient");
    return handleResponse(res);
  };
  
  export const createPatient = async (patient) => {
    const res = await fetch("/patient", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(patient),
    });
    return handleResponse(res);
  };
  
  export const updatePatient = async (id, patient) => {
    const res = await fetch(`/patient/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(patient),
    });
    return handleResponse(res);
  };
  
  export const deletePatient = async (id) => {
    const res = await fetch(`/patient/${id}`, {
      method: "DELETE",
    });
    return handleResponse(res);
  };
  