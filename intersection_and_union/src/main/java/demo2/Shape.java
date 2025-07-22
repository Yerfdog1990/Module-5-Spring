package demo2;

// Sealed base class - only certain subclasses are allowed
sealed public abstract class Shape permits Circle, Rectangle, Triangle{
}
