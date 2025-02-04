// src/types/auth.ts

export interface LoginFormValues {
    email: string;
    password: string;
}

export interface SignupFormValues {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    confirmPassword: string;
}

export interface FormikSubmitHandler {
    setSubmitting: (isSubmitting: boolean) => void;
    resetForm: () => void;
}

// src/types/Seat.ts
export interface Seat {
    id: number;
    location: string;
    status: string; // e.g., "Available", "Booked", "Requested"
  }
  